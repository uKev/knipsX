import java.util.ArrayList;
import java.util.List;


public class Picture implements PictureContainer {

	@Override
	public ArrayList<?> getItems() {		
		List<PictureContainer> temp = new ArrayList<PictureContainer>();
		temp.add(this);
		return (ArrayList<?>) temp;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}


}
