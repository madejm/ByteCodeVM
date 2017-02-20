package com.madejm.ByteCodeVM.BusinessLogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.madejm.ByteCodeVM.BusinessObjects.Models.ByteCode;

public class Parser {
	public static final short arch1 = 1;
	public static final short arch2 = 2;

	private Architecture arch;

	public Parser(short flag) {
		switch (flag) {
		case arch1:
			arch = new Arch1();
		case arch2:
			arch = new Arch2();
		}
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
			System.out.println(ln);
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

class ParseException extends Exception {
	private static final long serialVersionUID = 9158070077781811054L;

	public ParseException() {
		System.out.println("Error while parsing.");
	}
}

/*
 * 
 */
interface Architecture {
	ParseChain makeChain();
}

class Arch1 implements Architecture {
	public ParseChain makeChain() {
		ParseChain first = new ParsePARSEEND(null); // must be at the end
		first = new ParseHALT(first);
		first = new ParsePOP(first);
		first = new ParsePRINT(first);
		first = new ParseSTORE(first);
		first = new ParseGSTORE(first);
		first = new ParseLOAD(first);
		first = new ParseGLOAD(first);
		first = new ParseICONST(first);
		first = new ParseBR(first);
		first = new ParseBRT(first);
		first = new ParseBRF(first);
		first = new ParseHALT(first);
		first = new ParseIEQ(first);
		first = new ParseILT(first);
		first = new ParseIMUL(first);
		first = new ParseISUB(first);
		first = new ParseIADD(first);

		return first;
	}
}

class Arch2 implements Architecture {
	public ParseChain makeChain() {
		ParseChain first = new ParsePARSEEND(null); // must be at the end
		first = new ParseHALT(first);
		first = new ParsePOP(first);
		first = new ParsePRINT(first);
		first = new ParseSTORE(first);
		first = new ParseGSTORE(first);
		first = new ParseLOAD(first);
		first = new ParseGLOAD(first);
		first = new ParseICONST(first);
		first = new ParseBR(first);
		first = new ParseBRT(first);
		first = new ParseBRF(first);
		first = new ParseHALT(first);
		first = new ParseIEQ(first);
		first = new ParseILT(first);
		first = new ParseIMUL(first);
		first = new ParseISUB(first);
		first = new ParseIADD(first);

		return first;
	}
}

abstract class ParseChain {
	protected ParseChain next;

	ParseChain(ParseChain ch) {
		next = ch;
	}

	abstract ByteCode makeObject(String ln) throws Exception;
}

class ParseIADD extends ParseChain {
	ParseIADD(ParseChain ch) {
		super(ch);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		if (Pattern.matches("IADD", ln)) {
			return new ByteCode.IADD();
		} else
			return next.makeObject(ln);
	}

}

class ParseISUB extends ParseChain {
	ParseISUB(ParseChain ch) {
		super(ch);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		if (Pattern.matches("ISUB", ln)) {
			return new ByteCode.ISUB();
		} else
			return next.makeObject(ln);
	}
}

class ParseIMUL extends ParseChain {
	ParseIMUL(ParseChain ch) {
		super(ch);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		if (Pattern.matches("IMUL", ln)) {
			return new ByteCode.IMUL();
		} else
			return next.makeObject(ln);
	}
}

class ParseILT extends ParseChain {
	ParseILT(ParseChain ch) {
		super(ch);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		if (Pattern.matches("ILT", ln)) {
			return new ByteCode.ILT();
		} else
			return next.makeObject(ln);
	}
}

class ParseIEQ extends ParseChain {
	ParseIEQ(ParseChain ch) {
		super(ch);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		if (Pattern.matches("IEQ", ln)) {
			return new ByteCode.IEQ();
		} else
			return next.makeObject(ln);
	}
}

class ParseBR extends ParseChain {
	ParseBR(ParseChain ch) {
		super(ch);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		if (Pattern.matches("BR", ln)) {
			return new ByteCode.BR();
		} else
			return next.makeObject(ln);
	}
}

class ParseBRT extends ParseChain {
	ParseBRT(ParseChain ch) {
		super(ch);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		if (Pattern.matches("BRT", ln)) {
			return new ByteCode.BRT();
		} else
			return next.makeObject(ln);
	}
}

class ParseBRF extends ParseChain {
	ParseBRF(ParseChain ch) {
		super(ch);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		if (Pattern.matches("BRF", ln)) {
			return new ByteCode.BRF();
		} else
			return next.makeObject(ln);
	}
}

class ParseICONST extends ParseChain {
	ParseICONST(ParseChain ch) {
		super(ch);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		if (Pattern.matches("ICONST", ln)) {
			return new ByteCode.ICONST();
		} else
			return next.makeObject(ln);
	}
}

class ParseLOAD extends ParseChain {
	ParseLOAD(ParseChain ch) {
		super(ch);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		if (Pattern.matches("LOAD", ln)) {
			return new ByteCode.LOAD();
		} else
			return next.makeObject(ln);
	}
}

class ParseGLOAD extends ParseChain {
	ParseGLOAD(ParseChain ch) {
		super(ch);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		if (Pattern.matches("GLOAD", ln)) {
			return new ByteCode.GLOAD();
		} else
			return next.makeObject(ln);
	}
}

class ParseSTORE extends ParseChain {
	ParseSTORE(ParseChain ch) {
		super(ch);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		if (Pattern.matches("STORE", ln)) {
			return new ByteCode.STORE();
		} else
			return next.makeObject(ln);
	}
}

class ParseGSTORE extends ParseChain {
	ParseGSTORE(ParseChain ch) {
		super(ch);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		if (Pattern.matches(".GSTORE.", ln)) {
			return new ByteCode.GSTORE();
		} else
			return next.makeObject(ln);
	}
}

class ParsePRINT extends ParseChain {
	ParsePRINT(ParseChain ch) {
		super(ch);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		if (Pattern.matches("PRINT", ln)) {
			return new ByteCode.PRINT();
		} else
			return next.makeObject(ln);
	}
}

class ParsePOP extends ParseChain {
	ParsePOP(ParseChain ch) {
		super(ch);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		if (Pattern.matches("POP", ln)) {
			return new ByteCode.POP();
		} else
			return next.makeObject(ln);
	}
}

class ParseHALT extends ParseChain {
	ParseHALT(ParseChain ch) {
		super(ch);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		if (Pattern.matches("HALT", ln)) {
			return new ByteCode.HALT();
		} else
			return next.makeObject(ln);
	}
}

class ParsePARSEEND extends ParseChain {

	ParsePARSEEND(ParseChain ch) {
		super(null);
	}

	@Override
	ByteCode makeObject(String ln) throws Exception {
		System.out.println("ERROR: " + ln);
		throw new ParseException();
	}

}
