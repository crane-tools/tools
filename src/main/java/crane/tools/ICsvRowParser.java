package crane.tools;

/**
 * Created by crane on 16/7/21.
 */
public interface ICsvRowParser<T> {
    T getObject(String[] row);
}