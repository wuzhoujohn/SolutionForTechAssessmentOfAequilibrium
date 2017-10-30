package part2;

public class Transformer {

	private String name;
	private String team;
	private String strength;
	private String intelligence;
	private String speed;
	private String endurance;
	private String rank;
	private String courage;
	private String firepower;
	private String skill;
	private boolean isDestroyed;
	
	public Transformer(String name, String team, String strength, String intelligence, String speed, String endurance,
			String rank, String courage, String firepower, String skill) {
		this.name = name;
		this.team = team;
		this.strength = strength;
		this.intelligence = intelligence;
		this.speed = speed;
		this.endurance = endurance;
		this.rank = rank;
		this.courage = courage;
		this.firepower = firepower;
		this.skill = skill;
		this.isDestroyed = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Integer getStrength() {
		return Integer.parseInt(strength);
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public Integer getIntelligence() {
		return Integer.parseInt(intelligence);
	}

	public void setIntelligence(String intelligence) {
		this.intelligence = intelligence;
	}

	public Integer getSpeed() {
		return Integer.parseInt(speed);
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public Integer getEndurance() {
		return Integer.parseInt(endurance);
	}

	public void setEndurance(String endurance) {
		this.endurance = endurance;
	}

	public Integer getRank() {
		return Integer.parseInt(rank);
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Integer getCourage() {
		return Integer.parseInt(courage);
	}

	public void setCourage(String courage) {
		this.courage = courage;
	}

	public Integer getFirepower() {
		return Integer.parseInt(firepower);
	}

	public void setFirepower(String firepower) {
		this.firepower = firepower;
	}

	public Integer getSkill() {
		return Integer.parseInt(skill);
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	public Integer getOverallRating() {
		return Integer.parseInt(this.strength) +  Integer.parseInt(this.intelligence) + Integer.parseInt(this.speed) + Integer.parseInt(this.endurance) + Integer.parseInt(this.firepower);
	}
	

}
