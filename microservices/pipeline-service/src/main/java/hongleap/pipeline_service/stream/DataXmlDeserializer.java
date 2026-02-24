package hongleap.pipeline_service.stream;

import hongleap.pipeline_service.domain.Data;

public class DataXmlDeserializer extends XmlStringDeserializer<Data>{
    public DataXmlDeserializer() {
        super(Data.class);
    }
}
