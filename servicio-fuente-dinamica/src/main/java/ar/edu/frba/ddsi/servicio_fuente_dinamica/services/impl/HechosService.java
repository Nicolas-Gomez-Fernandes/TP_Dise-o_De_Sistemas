package ar.edu.frba.ddsi.servicio_fuente_dinamica.services.impl;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.input.HechoInputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.HechoOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.MultimediaOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.OrigenFuenteDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.Hecho;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.Usuario;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.hechos.Categoria;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.hechos.Multimedia;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.hechos.Ubicacion;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories.ICategoriaRepository;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories.IHechosRepository;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories.IMultimediaRepository;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories.IUsuarioRepository;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.services.IGestionMultimediaService;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.services.IHechosService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@Service
public class HechosService implements IHechosService {
  private IHechosRepository hechosRepository;
  @Autowired
  private ICategoriaRepository categoriaRepository;
  @Autowired
  private IUsuarioRepository usuarioRepository;
  @Autowired
  private IMultimediaRepository multimediaRepository;

  private Logger logger;

  HechosService(IHechosRepository hechosRepository) {
    this.hechosRepository = hechosRepository;
    this.logger = LoggerFactory.getLogger(HechosService.class);
  }

  @Override
  public List<HechoOutputDTO> buscarTodos() {
    List<Hecho> hechos =  this.hechosRepository.findAll().stream().filter( hecho -> !hecho.getEliminado()).toList();

    /*if(hechos.isEmpty()){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay hechos registrados");
    }*/
    return hechos.stream().map(this::hechoOutputDTO).toList();
  }

  @Override
  public HechoOutputDTO buscarPorId(Long id) {
    var hecho = this.hechosRepository.findById(id)
        .orElseThrow(() -> {
          log.error("Hecho de id {} no encontrado", id);
          return new ResponseStatusException(HttpStatus.NOT_FOUND, "Hecho no encontrado con id: " + id);
        });

    return this.hechoOutputDTO(hecho);
  }

  @Override
  public HechoOutputDTO crear(HechoInputDTO inputDTO){
    //Hecho hecho = new Hecho();

    if (inputDTO == null || inputDTO.getTitulo().isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los datos del hecho no pueden ser nulos");
    }

    if (inputDTO.getTitulo() == null || inputDTO.getTitulo().isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El título del hecho no puede estar vacío");
    }

    if(hechosRepository.existsByTitulo(inputDTO.getTitulo())){
      log.warn("El hecho con título '{}' ya existe. No se creará un nuevo hecho.", inputDTO.getTitulo());
      Hecho hecho = this.hechosRepository.findByTitulo(inputDTO.getTitulo());
      return this.hechoOutputDTO(hecho);
    }

    List<Multimedia> multimedia = this.multimediaRepository.findAllById(inputDTO.getArchivos_multimedia());

    Categoria categoria = this.categoriaRepository.findById(inputDTO.getId_categoria())
        .orElseThrow(()-> {
          log.error("Categoría de id {} no encontrada", inputDTO.getId_categoria());
          return new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada con id: " + inputDTO.getId_categoria());
        });

    //TODO -- List<Multimedia> multimediaUrls = this.gestorMultimediaService.guardarArchivos(inputDTO.getArchivosMultimedia());

    Usuario usuario = null;
    if (inputDTO.getId_contribuyente() != null) {
      usuario = this.usuarioRepository.findById(inputDTO.getId_contribuyente()).
          orElseThrow(() -> {
            log.error("Usuario de id {} no encontrado", inputDTO.getId_contribuyente());
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con id: " + inputDTO.getId_contribuyente());
          });
    }

    log.info("Creando hecho con título '{}'", inputDTO.getTitulo());

    Ubicacion ubicacion = new Ubicacion(inputDTO.getLatitud(), inputDTO.getLongitud());

    Hecho hecho = new Hecho();
    hecho.setArchivosMultimedia(multimedia);
    hecho.setFechaAcontencimiento(inputDTO.getFecha_acontecimiento());
    hecho.setFechaCreacion(LocalDateTime.now());
    hecho.setTitulo(inputDTO.getTitulo());
    hecho.setDescripcion(inputDTO.getDescripcion());
    hecho.setCategoria(categoria);
    hecho.setUbicacion(ubicacion);

    // TODO hecho.setLatitud(inputDTO.getLatitud());
    // TODO hecho.setLongitud(inputDTO.getLongitud());
    hecho.setEtiquetas(inputDTO.getEtiquetas());
    hecho.setContribuyente(usuario);
    hecho.setEliminado(false);

    this.hechosRepository.save(hecho);

    log.info("Hecho con título '{}' creado con id {}", hecho.getTitulo(), hecho.getId());
    return this.hechoOutputDTO(hecho);
  }

  @Override
  public void eliminar(Long id) {
    Hecho hecho = this.hechosRepository.findById(id)
        .orElseThrow(() -> {
          log.error("Hecho de id {} no encontrado", id);
          return new ResponseStatusException(HttpStatus.NOT_FOUND, "Hecho no encontrado con id: " + id);
        });
    hecho.setEliminado(true);
    this.hechosRepository.save(hecho);
  }

  private HechoOutputDTO hechoOutputDTO(Hecho hecho) {
    List<MultimediaOutputDTO> multimediaOutputDTO = hecho.getArchivosMultimedia().stream().map(this::multimediaToMultimediaOutputDTO).collect(Collectors.toList());
    return new HechoOutputDTO(
        hecho.getId(),
        hecho.getTitulo(),
        hecho.getDescripcion(),
        hecho.getCategoria(),
        hecho.getUbicacion().getLatitud(),
        hecho.getUbicacion().getLongitud(),
        hecho.getFechaAcontencimiento(),
        hecho.getFechaCreacion(),
        multimediaOutputDTO,
        hecho.getEtiquetas(),
        new OrigenFuenteDTO("Manual" ,"DINAMICA"));
  }

  private MultimediaOutputDTO multimediaToMultimediaOutputDTO(Multimedia multimedia) {
    return new MultimediaOutputDTO(
        multimedia.getId(),
        multimedia.getRutaArchivo(),
        multimedia.getTipoArchivo()
    );
  }
}
