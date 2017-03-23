package crane.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by crane on 2017/3/23.
 */
public class XmlUtils {

    /**
     * @param jaxbElement    要转化xml的对象
     * @param printOnConsole 是否打印到控制台
     * @param classes        要转化xml的对象中包含的class
     * @return 格式化的xml字符串
     * @throws JAXBException
     * @throws IOException
     */
    public static String toXmlString(Object jaxbElement, boolean printOnConsole, Class... classes) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(classes);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        if (printOnConsole) {
            marshaller.marshal(jaxbElement, System.out);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(jaxbElement, baos);
        String xmlObj = new String(baos.toByteArray());
        baos.close();
        return xmlObj;
    }

    /**
     * xml字符串反序列化对象
     *
     * @param xml     要解析的xml字符串
     * @param classes xml字符串中涉及的class
     * @param <T>     返回类型
     * @return
     * @throws JAXBException
     */
    public static <T> T xmlToBean(String xml, Class... classes) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(classes);
        Unmarshaller um = context.createUnmarshaller();
        StringReader stringReader = new StringReader(xml);
        T t = (T) um.unmarshal(stringReader);
        stringReader.close();
        return t;
    }

//    @XmlRootElement(name = "department")
//    public class Department {
//
//        private String name;
//        private List<Staff> staffs;
//
//        public String getName() {
//            return name;
//        }
//
//        @XmlAttribute
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public List<Staff> getStaffs() {
//            return staffs;
//        }
//
//        @XmlElementWrapper(name = "staffs")
//        @XmlElement(name = "staff")
//        public void setStaffs(List<Staff> staffs) {
//            this.staffs = staffs;
//        }
//    }
//
//
//    @XmlRootElement(name = "staff")
//    public class Staff {
//
//        private String name;
//        private int age;
//        private boolean smoker;
//
//        @XmlElement
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        @XmlElement
//        public int getAge() {
//            return age;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }
//
//        @XmlAttribute
//        public boolean isSmoker() {
//            return smoker;
//        }
//
//        public void setSmoker(boolean smoker) {
//            this.smoker = smoker;
//        }
//    }

}

