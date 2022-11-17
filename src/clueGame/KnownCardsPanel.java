package clueGame;
//REFACTOR THIS METHOD, RECALL UPDATE FUNCTIONS
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class KnownCardsPanel extends JPanel {
	private Player player;

	private JPanel people;
	private JPanel rooms;
	private JPanel weapons;

	public KnownCardsPanel(Player player) {
		//given human player
		this.player = player;

		//create pure vertical layout
		GridLayout cardsLayout = new GridLayout(0,1);
		this.setLayout(cardsLayout);

		//create main title border
		Border title = BorderFactory.createTitledBorder("Known Cards");
		setBorder(title);

		//create three panels for different cards
		people = new JPanel(new GridLayout(0,1));
		rooms = new JPanel(new GridLayout(0,1));
		weapons = new JPanel(new GridLayout(0,1));

		//call methods to fill panels 
		createPeoplePanel();
		createRoomsPanel();
		createWeaponsPanel();

		//add all panels to main
		add(people);
		add(rooms);
		add(weapons);

	}

	public void createWeaponsPanel() {
		//remove all to create from scratch
		weapons.removeAll();

		//create titled border
		Border title = BorderFactory.createTitledBorder("Weapons");
		weapons.setBorder(title);

		//create panel for each in hand
		JPanel inHandWeapons = new JPanel(new GridLayout(0,1));

		//create labels for each part of panel
		JLabel inHand = new JLabel("In Hand:");
		JLabel inSeen = new JLabel("Seen:");
		inHandWeapons.add(inHand);

		//newCard to hold each card and boolean to detect if there are no cards of type
		JTextField newCard;
		boolean none = false;

		//for each card in hand detect type, if the right type add to the panel
		for(Card weapon : player.getCards()) {
			if(weapon.getType() == CardType.WEAPON) {
				newCard = new JTextField(weapon.getName());
				inHandWeapons.add(newCard);
				none = true;
			}
		}
		//if no cards of the right type were detected then default to None
		if (none == false){
			newCard = new JTextField("None");
			inHandWeapons.add(newCard);
		}

		//create seen panel
		JPanel SeenWeapons = new JPanel(new GridLayout(0,1));
		SeenWeapons.add(inSeen);

		//reset bool to false
		none = false;
		//for each seen card detect type, if the right type add to the panel
		for(Card weapon : player.getSeenCards()) {
			if(weapon.getType() == CardType.WEAPON) {
				newCard = new JTextField(weapon.getName());
				newCard.setPreferredSize(new Dimension(300,25));
				SeenWeapons.add(newCard);
				none = true;
			}
		}
		//if no cards of the right type were detected then default to None
		if (none == false){
			newCard = new JTextField("None");
			newCard.setPreferredSize(new Dimension(300,25));
			SeenWeapons.add(newCard);
		}

		//add both panels to main weapons panel
		weapons.add(inHandWeapons);
		weapons.add(SeenWeapons);

	}

	public void createRoomsPanel() {
		//remove all to create from scratch
		rooms.removeAll();

		//create titled border
		Border title = BorderFactory.createTitledBorder("Rooms");
		rooms.setBorder(title);

		//create panel for each in hand
		JPanel inHandRooms = new JPanel(new GridLayout(0,1));

		//create labels for each part of panel
		JLabel inHand = new JLabel("In Hand:");
		JLabel inSeen = new JLabel("Seen:");
		inHandRooms.add(inHand);

		//newCard to hold each card and boolean to detect if there are no cards of type
		JTextField newCard;
		boolean none = false;

		//for each card in hand detect type, if the right type add to the panel
		for(Card room : player.getCards()) {
			if(room.getType() == CardType.ROOM) {
				newCard = new JTextField(room.getName());
				newCard.setPreferredSize(new Dimension(90,25));
				inHandRooms.add(newCard);
				none = true;
			}
		}
		//if no cards of the right type were detected then default to None
		if (none == false){
			newCard = new JTextField("None");
			newCard.setPreferredSize(new Dimension(90,25));
			inHandRooms.add(newCard);
		}
		//create seen panel
		JPanel SeenRooms = new JPanel(new GridLayout(0,1));
		SeenRooms.add(inSeen);

		//reset bool to false
		none = false;
		//for each seen card detect type, if the right type add to the panel
		for(Card room : player.getSeenCards()) {
			if(room.getType() == CardType.ROOM) {
				newCard = new JTextField(room.getName());
				newCard.setPreferredSize(new Dimension(90,25));
				SeenRooms.add(newCard);
				none = true;
			}
		}
		//if no cards of the right type were detected then default to None
		if (none == false){
			newCard = new JTextField("None");
			newCard.setPreferredSize(new Dimension(90,25));
			SeenRooms.add(newCard);
		}

		//add both panels to main rooms panel
		rooms.add(inHandRooms);
		rooms.add(SeenRooms);
	}

	public void createPeoplePanel() {
		//remove all to create from scratch
		people.removeAll();

		//create titled border
		Border title = BorderFactory.createTitledBorder("People");
		people.setBorder(title);

		//create panel for each in hand
		JPanel inHandPeople = new JPanel(new GridLayout(0,1));

		//create labels for each part of panel
		JLabel inHand = new JLabel("In Hand:");
		JLabel inSeen = new JLabel("Seen:");
		inHandPeople.add(inHand);

		//newCard to hold each card and boolean to detect if there are no cards of type
		JTextField newCard;
		boolean none = false;

		//for each card in hand detect type, if the right type add to the panel
		for(Card person : player.getCards()) {
			if(person.getType() == CardType.PERSON) {
				newCard = new JTextField(person.getName());
				newCard.setPreferredSize(new Dimension(90,25));
				inHandPeople.add(newCard);
				none = true;
			}
		}
		//if no cards of the right type were detected then default to None
		if (none == false){
			newCard = new JTextField("None");
			newCard.setPreferredSize(new Dimension(90,25));
			inHandPeople.add(newCard);
		}
		//create seen panel
		JPanel SeenPeople = new JPanel(new GridLayout(0,1));
		SeenPeople.add(inSeen);

		//reset bool to false
		none = false;
		//for each seen card detect type, if the right type add to the panel

		for(Card person : player.getSeenCards()) {
			if(person.getType() == CardType.PERSON) {
				newCard = new JTextField(person.getName());
				newCard.setPreferredSize(new Dimension(300,25));
				SeenPeople.add(newCard);
				none = true;
			}
		}
		//if no cards of the right type were detected then default to None
		if (none == false){
			newCard = new JTextField("None");
			newCard.setPreferredSize(new Dimension(300,25));
			SeenPeople.add(newCard);
		}

		//add both panels to main people panel
		people.add(inHandPeople);
		people.add(SeenPeople);

	}
}
