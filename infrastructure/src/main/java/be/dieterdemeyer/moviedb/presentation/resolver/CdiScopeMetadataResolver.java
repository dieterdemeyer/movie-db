package be.dieterdemeyer.moviedb.presentation.resolver;

import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ScopeMetadata;

public class CdiScopeMetadataResolver extends AnnotationScopeMetadataResolver {

    @Override
    public ScopeMetadata resolveScopeMetadata(BeanDefinition definition) {
        ScopeMetadata metadata = new ScopeMetadata();
        if (definition instanceof AnnotatedBeanDefinition) {
            AnnotatedBeanDefinition annDef = (AnnotatedBeanDefinition) definition;
            Set<String> annotationTypes = annDef.getMetadata().getAnnotationTypes();

            if (annotationTypes.contains(javax.faces.bean.RequestScoped.class.getName()) || annotationTypes.contains(javax.enterprise.context.RequestScoped.class.getName())) {
                metadata.setScopeName("request");
            } else if (annotationTypes.contains(javax.faces.bean.SessionScoped.class.getName()) || annotationTypes.contains(javax.enterprise.context.SessionScoped.class.getName())) {
                metadata.setScopeName("session");
            } else {
                return super.resolveScopeMetadata(definition);
            }
        }
        return metadata;
    }
}
