package eu.jrie.nasa.spaceapps.fireshield.rest.model.response.hateoas;

import eu.jrie.nasa.spaceapps.fireshield.model.Fire;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

public class FireModel extends EntityModel<Fire> {
    public FireModel(Fire content, Link... links) {
        super(content, links);
    }
}// implements SimpleRepresentationModelAssembler<Fire> {



//    @Override
//    public void addLinks(EntityModel<Fire> resource) {
//
//    }
//
//    @Override
//    public void addLinks(CollectionModel<EntityModel<Fire>> resources) {
//
//    }
//}
