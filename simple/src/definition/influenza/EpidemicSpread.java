package situations.esper.definition.influenza;

import situations.esper.control.Situation;

import com.espertech.esper.client.EventBean;

public class EpidemicSpread extends Situation {
	private Epidemic[] epidemics = new Epidemic[2];
	
	public EpidemicSpread(){
		
		setSitName("EpidemicSpread");
		
		setEplA("select e1 as epidemic1, e2 as epidemic2, e1.key as key1, e2.key as key2, cityDistance(e1.city, e2.city) as distance "
				+ "from pattern ["
				+ "              every "
				+ "                   ("
				+ "                     ("
				+ "                       every e1=Epidemic(actived=true) -> "
				+ "                      (every e2=Epidemic(actived=true, city.name != e1.city.name)"
				+ "                       and not Epidemic(actived=true, id = e1.id))"
				+ "                     )"
				+ ""
				+ "                     or"
				+ ""
				+ "                    ("
				+ "                       every e2=Epidemic(actived=true) -> "
				+ "                      (every e1=Epidemic(actived=true, city.name != e1.city.name)"
				+ "                       and not Epidemic(actived=true, id = e2.id))"
				+ "                    )"
				+ "                  )"
				+ "             ] "
				+ "where cityDistance(e1.city, e2.city) > 100");
		/*
		setEplD("select es, es.epidemics[0].key as key1, es.epidemics[1].key as key2 "
				+ "from EpidemicSpread.std:unique(id) as es, Epidemic.win:keepall() as e "
				  + "where es.actived = true "
				  + "and"
				  + "   ("
				  + "    (es.epidemics[0].id = e.id and e.actived = false) "
				  + "    or "
				  + "    (es.epidemics[1].id = e.id and e.actived = false) "
				  + "   )");
		*/
		setEplD("select es, es.epidemics[0].key as key1, es.epidemics[1].key as key2 "
				+ "from "
				+ "		EpidemicSpread.std:unique(id) as es, "
				+ "		Epidemic.std:lastevent() as e "
				  + "where es.actived = true "
				  + "and"
				  + "   ("
				  + "    (es.epidemics[0].id = e.id and e.actived = false) "
				  + "    or "
				  + "    (es.epidemics[1].id = e.id and e.actived = false) "
				  + "   )");
	}
	
	public Epidemic getEpidemics(int index) {
		return epidemics[index];
	}

	public void setEpidemics(int index, Epidemic e) {
		epidemics[index] = e;
	}
	
	@Override
	public Situation createNewSit(EventBean event) {
		EpidemicSpread epidemicSpread = new EpidemicSpread();
		try{
			epidemicSpread.setSitName("EpidemicSpread");
			epidemicSpread.setEpidemics(0, (Epidemic)event.get("epidemic1"));
			epidemicSpread.setEpidemics(1, (Epidemic)event.get("epidemic2"));
			
			/* Apagar */
			//System.out.println((double)event.get("distance"));
			System.out.println(epidemicSpread.getEpidemics(0).getStart_time());
			System.out.println(epidemicSpread.getEpidemics(0).getEnd_time());
			/* End */
		}catch(Exception e){
    		System.out.println("EpidemicSpread: " + e);
    	}
		
		return epidemicSpread;
	}

	@Override
	public Object doActionAtCreateDeactivationEvent() {
		EpidemicSpread epidemicSpread = new EpidemicSpread();
		try{
			epidemicSpread.setSitName("EpidemicSpread");
			epidemicSpread.setEpidemics(0, this.getEpidemics(0));
			epidemicSpread.setEpidemics(1, this.getEpidemics(1));
		}catch(Exception e){
    		System.out.println("EpidemicSpread: " + e);
    	}
		
		return epidemicSpread;
	}

}
