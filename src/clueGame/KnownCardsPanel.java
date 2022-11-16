package clueGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Set;

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
		getHandPanel(inHandWeapons, player.getCards(), CardType.WEAPON);
		
		//create seen panel
		JPanel SeenWeapons = new JPanel(new GridLayout(0,1));
		SeenWeapons.add(inSeen);

		getSeenPanel(SeenWeapons, player.getSeenCards(), CardType.WEAPON);
		
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

		getHandPanel(inHandRooms, player.getCards(), CardType.ROOM);

		//create seen panel
		JPanel SeenRooms = new JPanel(new GridLayout(0,1));
		SeenRooms.add(inSeen);

		getSeenPanel(SeenRooms, player.getSeenCards(), CardType.ROOM);


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

		getHandPanel(inHandPeople, player.getCards(), CardType.PERSON);

		//create seen panel
		JPanel SeenPeople = new JPanel(new GridLayout(0,1));
		SeenPeople.add(inSeen);
		
		//for each seen card detect type, if the right type add to the panel
		getSeenPanel(SeenPeople, player.getSeenCards(), CardType.PERSON);

		//add both panels to main people panel
		people.add(inHandPeople);
		people.add(SeenPeople);

	}
	
	//for each card in hand detect type, if the right type add to the panel
	public void getHandPanel(JPanel handPanel, ArrayList<Card> hand, CardType type) {
		JTextField newCard;
		boolean none = false;
		for(Card card : hand) {
			if(card.getType() == type) {
				newCard = new JTextField(card.getName());
				newCard.setPreferredSize(new Dimension(300,25));
				handPanel.add(newCard);
				none = true;
			}
		}
		
		if (none == false){
			newCard = new JTextField("None");
			newCard.setPreferredSize(new Dimension(300,25));
			handPanel.add(newCard);
		}
	}
	
	//for each seen card detect type, if the right type add to the panel
	public void getSeenPanel(JPanel Seen, Set<Card> seenCards, CardType type) {
		JTextField newCard;
		boolean none = false;
		for(Card card : seenCards) {
			if(card.getType() == type) {
				newCard = new JTextField(card.getName());
				newCard.setPreferredSize(new Dimension(300,25));
				Seen.add(newCard);
				none = true;
			}
		}
		
		if (none == false){
			newCard = new JTextField("None");
			newCard.setPreferredSize(new Dimension(300,25));
			Seen.add(newCard);
		}
	}
}
