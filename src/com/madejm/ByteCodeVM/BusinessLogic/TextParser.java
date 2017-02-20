package com.madejm.ByteCodeVM.BusinessLogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.madejm.ByteCodeVM.BusinessObjects.Abstractions.ParseChain;
import com.madejm.ByteCodeVM.BusinessObjects.Interfaces.Architecture;
import com.madejm.ByteCodeVM.BusinessObjects.Models.ByteCode;

public class TextParser {

	private Architecture arch;

	public TextParser() {
	}

	public void setStrategicArchitecture(Architecture arch) {
		this.arch = arch;
	}

	public ByteCode[] parse(String path) throws Exception {
		FileReader fileReader = null;
		fileReader = new FileReader(path);

		List<ByteCode> tmplist = new ArrayList<ByteCode>();
		BufferedReader bufferReader = new BufferedReader(fileReader);
		String ln;
		String[] commands;
		ParseChain first = arch.makeChain();

		while ((ln = bufferReader.readLine()) != null) {
			commands = (ln.replaceAll(",", "").replaceAll(";", "")).trim()
					.split(" ");
			tmplist.add(first.makeObject(commands[0].toUpperCase()));

			for (int i = 1; i < commands.length; i++) {
				tmplist.add(makeVlue(commands[i]));
			}

		}

		fileReader.close();

		ByteCode[] bc = new ByteCode[tmplist.size()];
		for (int i = 0; i < tmplist.size(); i++)
			bc[i] = tmplist.get(i);

		return bc;
	}

	private static ByteCode makeVlue(String ln) {
		int v = Integer.parseInt(ln);
		return new ByteCode.VALUE(v);
	}
}
