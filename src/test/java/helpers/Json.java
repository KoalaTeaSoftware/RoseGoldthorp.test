package helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import org.json.JSONObject;
import org.junit.Assert;

import java.net.URLEncoder;
import java.util.Iterator;

import static com.google.gson.JsonParser.parseString;

public class Json {
    /**
     * Takes a JSON object and makes it nto the same sort of string as the browser would if it was required to encode the contents of a form
     * We need to write this, as there is no direct, built-in to do this.
     *
     * @param in - Most likely the JSON-shaped body that you would like to send to a service
     * @return A string that works with | content-type | application/x-www-form-urlencoded |
     */
    public static String toFormEncoded(JSONObject in) {
        try {
            StringBuilder out = new StringBuilder();

            Iterator<String> x = in.keys();
            while (x.hasNext()) {
                String key = x.next();
                String value = in.get(key).toString();
                if (out.length() > 0)
                    out.append("&");
                out.append(URLEncoder.encode(key, "UTF-8"));
                out.append('=');
                out.append(URLEncoder.encode(value, "UTF-8"));
            }
            return out.toString();
        } catch (Exception e) {
            Assert.fail("[error] Unexpected crash:" + e.getMessage() + ":");
        }
        return "This is needed for code compliance";
    }

    /**
     * Make a stringified version of some JSON appear as pretty as possible.
     *
     * @param input - Something that you hope is JSON
     * @return - A string that has line-breaks and indentations
     * <p>
     * If the input can not be parsed, you will get back whatever you put in
     */
    public static String indentJson(String input) {
        try {
            if (input != null) {
                String output;
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonElement el = parseString(input);
                output = gson.toJson(el);

                return output;
            }
        } catch (JsonSyntaxException ignore) {
            // This is being fairly robust in that, if it can't be parsed, then the input is thrown back out
        }
        return input;
    }
}
