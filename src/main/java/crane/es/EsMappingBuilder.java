package crane.es;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;

/**
 * Created by crane on 16/9/20.
 */
public class EsMappingBuilder {
    public enum FieldType {
        STRING, DOUBLE, LONG;

        /**
         * Returns the name of this enum constant, as contained in the
         * declaration.  This method may be overridden, though it typically
         * isn't necessary or desirable.  An enum type should override this
         * method when a more "programmer-friendly" string form exists.
         *
         * @return the name of this enum constant
         */
        @Override
        public String toString() {
            return this.toString().toLowerCase();
        }
    }

    public enum Analyzer {
        ik_max_word, ik_smart
    }

    public enum TermVector {
        with_positions_offsets
    }

    private String curFieldType = "";
    private XContentBuilder mappingInfo;
    private boolean isFieldClosed = true;

    public EsMappingBuilder(String typeName) throws IOException {
        this.mappingInfo = XContentFactory.jsonBuilder()
                .startObject()
                .startObject(typeName)
                .startObject("properties");
    }

    public EsMappingBuilder newField(String fieldName, FieldType type) throws IOException {
        if (!isFieldClosed) {
            this.mappingInfo.endObject();
        }
        this.mappingInfo.startObject(fieldName).field("type", type.toString());
        this.curFieldType = type.toString();
        this.isFieldClosed = false;
        return this;
    }

    /**
     * 是否分词
     *
     * @param isFieldAnalyzed
     * @return
     * @throws IOException
     */
    public EsMappingBuilder setFieldAnalyzed(boolean isFieldAnalyzed) throws IOException {
        if (!"string".equals(this.curFieldType)) {
            return this;
        }
        if (isFieldAnalyzed) {
            this.mappingInfo.field("index", "analyzed");
        } else {
            this.mappingInfo.field("index", "not_analyzed");
        }
        return this;
    }

    public EsMappingBuilder setFieldIncludeInAll(boolean isIncludeInAll) throws IOException {
        this.mappingInfo.field("include_in_all", Boolean.toString(isIncludeInAll));
        return this;
    }

    public EsMappingBuilder setFieldStored(boolean isStored) throws IOException {
        this.mappingInfo.field("store", Boolean.toString(isStored));
        return this;
    }

    public EsMappingBuilder setFieldAnalyzer(Analyzer analyzer) throws IOException {
        if (!"string".equals(this.curFieldType)) {
            return this;
        }
        this.mappingInfo.field("analyzer", analyzer.toString());
        return this;
    }

    public EsMappingBuilder setFieldSearchAnalyzer(Analyzer analyzer) throws IOException {
        if (!"string".equals(this.curFieldType)) {
            return this;
        }
        this.mappingInfo.field("search_analyzer", analyzer.toString());
        return this;
    }

    public EsMappingBuilder setTermVector(TermVector termVector) throws IOException {
        if (!"string".equals(this.curFieldType)) {
            return this;
        }
        this.mappingInfo.field("term_vector", termVector.toString());
        return this;
    }

    public EsMappingBuilder setBoost(int boost) throws IOException {
        if (!"string".equals(this.curFieldType)) {
            return this;
        }
        this.mappingInfo.field("boost", boost + "");
        return this;
    }

    public XContentBuilder getMapping() throws IOException {
        if (!isFieldClosed) {
            this.mappingInfo.endObject();
            this.isFieldClosed = true;
        }
        this.mappingInfo.endObject();
        this.mappingInfo.endObject();
        this.mappingInfo.endObject();
        return this.mappingInfo;
    }
}
