package lawFirm.rest;

import lawFirm.entities.Lawyer;
import lawFirm.persistence.LawyerDAO;
import lawFirm.rest.contracts.LawyerDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/lawyers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LawyersController {

    @Inject
    private LawyerDAO lawyerDAO;

    @GET
    public List<LawyerDto> getAll() {
        return lawyerDAO.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Lawyer lawyer = lawyerDAO.findOne(id);
        if (lawyer == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(toDto(lawyer)).build();
    }

    @POST
    @Transactional
    public Response create(LawyerDto dto) {
        Lawyer lawyer = new Lawyer();
        lawyer.setName(dto.getName());
        lawyer.setSpecialization(dto.getSpecialization());
        lawyerDAO.persist(lawyer);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, LawyerDto dto) {
        Lawyer lawyer = lawyerDAO.findOne(id);
        if (lawyer == null) return Response.status(Response.Status.NOT_FOUND).build();
        lawyer.setName(dto.getName());
        lawyer.setSpecialization(dto.getSpecialization());
        lawyerDAO.update(lawyer);
        return Response.ok().build();
    }

    private LawyerDto toDto(Lawyer l) {
        LawyerDto dto = new LawyerDto();
        dto.setName(l.getName());
        dto.setSpecialization(l.getSpecialization());
        return dto;
    }
}
