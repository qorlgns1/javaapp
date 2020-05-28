import java.util.ArrayList;
import java.util.Comparator;


public class DTO_Main {

	public static void main(String[] args) {
		ArrayList <DTO> list = new ArrayList<>();
		list.add(new DTO(1,"배기훈",90,"930610"));
		list.add(new DTO(2,"임성현",85,"930623"));
		list.add(new DTO(3,"배성민",10,"930830"));
		list.add(new DTO(4,"강준희",40,"930604"));
		list.add(new DTO(5,"김현준",60,"930416"));
		
		list.sort(new Comparator<DTO>(){

			@Override
			public int compare(DTO o1, DTO o2) {
				
				return o1.getScore()-o2.getScore();
			}
			
		});

		for(DTO dto : list) {
			System.out.println(dto);
		}
	}

}
