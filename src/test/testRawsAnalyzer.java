package test;

import core.preprocess.index.RawsAnalyzer;
import core.preprocess.index.originalPageGetter;

public class testRawsAnalyzer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		RawsAnalyzer analyzer = new RawsAnalyzer("Raws");
//		analyzer.createPageIndex();
		
		originalPageGetter getter = new originalPageGetter("http://www.sohu.com");
		getter.getPage();
	}

}
