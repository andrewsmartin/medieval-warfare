package newworldorder.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import newworldorder.common.matchmaking.GameRequest;
import newworldorder.common.network.MessageConsumer;
import newworldorder.common.network.MessageHandler;
import newworldorder.common.network.MessageProducer;
import newworldorder.common.network.factory.ActorFactory;
import newworldorder.common.network.message.JoinGameCommand;

public class JoinGamePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainView aMainView;
	public JoinGamePanel(MainView pMainView){
		super();
		aMainView = pMainView;
		setLayout( new BorderLayout() );
	
		JPanel northPanel = new JPanel();
		JLabel jlabel = new JLabel("Join a Game");
	    jlabel.setFont(new Font("Calibri",1,60));
	    northPanel.add(jlabel);
	    northPanel.setOpaque(false);
	    add(northPanel, BorderLayout.NORTH);
		
		JButton back = new JButton("Back");
		back.setBackground(new Color(220, 20, 60));
	    back.setForeground(Color.WHITE);
	    back.setFocusPainted(false);
	    back.setFont(new Font("Calibri", Font.BOLD, 20));
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		back.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				aMainView.setLoginFromJoin();
			}	
		});
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));	
		
		JButton join = new JButton("Join");
		join.setAlignmentX(Component.CENTER_ALIGNMENT);
        join.setBackground(new Color(59, 89, 182));
        join.setForeground(Color.WHITE);
        join.setFocusPainted(false);
        join.setFont(new Font("Calibri", Font.BOLD, 20));
        join.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				aMainView.setMatchMaking();
				try {
					MessageProducer producer = ActorFactory.createDirectProducer("142.157.148.57", "requestQueue");
					GameRequest curRequest = new GameRequest(aMainView.getName(), 2);
					JoinGameCommand joinGameCommand = new JoinGameCommand(aMainView.getName(), curRequest);
					producer.sendCommand(joinGameCommand);
					
					// Now wait for a response from server
					MessageConsumer consumer = ActorFactory.createRoutingConsumer("142.157.148.57", "notifyExchange", aMainView.getName(), 
							new MessageHandler() {

								@Override
								public void handle(byte[] message) throws IOException, ClassNotFoundException {
									JOptionPane.showMessageDialog(null, "Game found!");
								}
						
					});
					
					consumer.consumeMessages();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}	
		});
        southPanel.add(join);
        southPanel.add(back);
		southPanel.setOpaque(false);
		add(southPanel, BorderLayout.SOUTH);
	    setOpaque(false);
	}    
}