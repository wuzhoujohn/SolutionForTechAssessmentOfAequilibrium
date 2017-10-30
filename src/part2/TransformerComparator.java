package part2;

import java.util.Comparator;

public class TransformerComparator implements Comparator<Transformer> {


	@Override
	public int compare(Transformer arg0, Transformer arg1) {
		// TODO Auto-generated method stub
		Integer ob1 = arg0.getRank();
		Integer ob2 = arg1.getRank();
		return (0 - ob1.compareTo(ob2));
	}

}
