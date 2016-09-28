package pl.natekrank.model.builder;

/**
 * Created by ievgenii on 24.09.16.
 */
public class ModelBuilderFactory {

    public static <BUILDER extends Builder> BUILDER getBuilder(Class<BUILDER> builderClass) throws InstantiationException, IllegalAccessException{
            return builderClass.newInstance();
    }
}
