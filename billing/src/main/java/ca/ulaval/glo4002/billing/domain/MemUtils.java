package ca.ulaval.glo4002.billing.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MemUtils {

	public byte[] saveData(Object object) {
		byte[] saveMem = new byte[0];
		ByteArrayOutputStream memory = new ByteArrayOutputStream();
		try {
			ObjectOutputStream writer = new ObjectOutputStream(memory);
			writer.writeObject(object);
			saveMem = memory.toByteArray();
			writer.close();
		} catch (IOException e) {
			return saveMem;
		}
		return saveMem;
	}

	public Object returnData(byte[] mem) {
		ByteArrayInputStream memory = new ByteArrayInputStream(mem);
		Object object;
		try {
			ObjectInputStream reader = new ObjectInputStream(memory);
			object = reader.readObject();

			reader.close();
		} catch (IOException e) {
			return new ArrayList<>();
		} catch (ClassNotFoundException e) {
			return new ArrayList<>();
		}
		return object;
	}

}
