package crane.tools;

import com.csvreader.CsvReader;
import org.apache.commons.lang.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by crane on 16/7/21.
 */
public class CsvHelper {
    private String filePath;
    private boolean hasHeader = true;
    private char spliter = ',';
    private String charSetName = "UTF-8";
    private List<String[]> rows;

    public String getCharSetName() {
        return charSetName;
    }

    public void setCharSetName(String charSetName) {
        this.charSetName = charSetName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isHasHeader() {
        return hasHeader;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public char getSpliter() {
        return spliter;
    }

    public void setSpliter(char spliter) {
        this.spliter = spliter;
    }

    public CsvHelper() {
    }

    public CsvHelper(String filePath) {
        if (StringUtils.isBlank(this.filePath)) {
            throw new IllegalArgumentException("param filePath empty");
        }
        this.filePath = filePath;
    }

    public void readCsv() throws IOException {
        if (StringUtils.isBlank(this.filePath)) {
            throw new IllegalArgumentException("please set filePath");
        }
        this.rows = null;
        CsvReader reader = null;
        try {
            reader = new CsvReader(new FileInputStream(this.filePath), this.spliter, Charset.forName(this.charSetName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String[]> records = new ArrayList<>();
        if (this.hasHeader) {
            reader.readHeaders();
        }
        while (reader.readRecord()) {
            String[] rowData = reader.getValues();
            records.add(rowData);
        }
        this.rows = records;
    }

    public List<String[]> getList() {
        return this.rows;
    }

    public <T> List<T> getList(ICsvRowParser<T> parser) {
        if (parser == null) {
            throw new IllegalArgumentException("ICsvRowParser param parser null");
        }
        if (this.rows == null || this.rows.size() <= 0) {
            throw new RuntimeException(String.format("no records in file: %s ", this.filePath));
        }
        List<T> list = new ArrayList<>();
        for (String[] row : this.rows) {
            list.add(parser.getObject(row));
        }
        return list;
    }
}
