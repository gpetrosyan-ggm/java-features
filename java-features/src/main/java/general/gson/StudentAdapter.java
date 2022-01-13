package general.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class StudentAdapter extends TypeAdapter<Student> {

    @Override
    public void write(JsonWriter writer, Student student) throws IOException {
        writer.beginObject();
        writer.name("name-1");
        writer.value(student.getName());
        writer.name("rollNo-1");
        writer.value(student.getRollNo());
        writer.endObject();
    }

    @Override
    public Student read(JsonReader reader) throws IOException {
        Student student = new Student();
        reader.beginObject();

        String fieldname = null;
        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                fieldname = reader.nextName();
            }

            if ("name".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                student.setName(reader.nextString());
            }

            if ("rollNo".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                student.setRollNo(reader.nextInt());
            }
        }
        reader.endObject();
        return student;
    }

}
