package de.dataport.window;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.dataport.standardcatalog.StandardContent;
import de.dataport.system.Painter;
import de.dataport.system.Serializer;
import de.dataport.system.Speicher;
import de.dataport.system.Speicher_Enum;

public class Menu extends JMenuBar
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Menu_State state;
	JMenuBar menubar;
	JMenu datei, modus, level, editor, info;
	JMenuItem beenden, singleplayer, multiplayer, leveleditor, level_laden, editor_new, editor_load, editor_save, hauptmenue, info_screen;
	static JCheckBoxMenuItem level_ton;
	
	public static JCheckBoxMenuItem getLevel_ton()
	{
		return level_ton;
	}


	public Menu (){
		this.menubar =new JMenuBar();
		menubar.setBounds(0,0,Fullscreen.desktopPane.getWidth(), 24);
		Fullscreen.desktopPane.add(menubar);
		datei = new JMenu("Datei");
		setStandardMenu(datei);
		menubar.add(datei);
		
		hauptmenue = new JMenuItem("Hauptmenü");
		setStandardMenu(hauptmenue);
		hauptmenue.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Fullscreen.callStart();
				changeMenu(Menu_State.MODUS);
			}
		});
		datei.add(hauptmenue);
		
		datei.setVisible(false);
		beenden = new JMenuItem("Beenden");
		setStandardMenu(beenden);
		beenden.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				Menu_Elemente.beenden();
			}
		
		});
		datei.add(beenden);
		
		
		/* MODUS MENU*/
		modus = new JMenu("Modus");
		setStandardMenu(modus);
		menubar.add(modus);
		modus.setVisible(false);
		
		singleplayer = new JMenuItem("Singleplayer");
		setStandardMenu(singleplayer);
		singleplayer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				Fullscreen.callGame();
				changeMenu(Menu_State.SINGLEPLAYER);
			}
			
		});
		modus.add(singleplayer);
		
		multiplayer = new JMenuItem("Multiplayer");
		setStandardMenu(multiplayer);
		multiplayer.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				Fullscreen.callMultiplayer();
			}
		});
		modus.add(multiplayer);
		
		leveleditor = new JMenuItem("Leveleditor");
		setStandardMenu(leveleditor);
		leveleditor.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				Fullscreen.callLeveleditor();
			}
		});
		modus.add(leveleditor);
		
		/* SINGLEPLAYER MENU*/
		level = new JMenu("Level");
		setStandardMenu(level);
		menubar.add(level);
		level.setVisible(false);
		
		level_laden = new JMenuItem("laden");
		setStandardMenu(level_laden);
		level_laden.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Painter.run = false;
				Game.Level();
				
			}
		});
		level.add(level_laden);
		
		level_ton = new JCheckBoxMenuItem("Ton");
		setStandardMenu(level_ton);
		level_ton.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				if (level_ton.isSelected()) {
					Speicher.SpeicherBoolean(Speicher_Enum.SOUND2, false);

				} else {

					Speicher.SpeicherBoolean(Speicher_Enum.SOUND2, true);
				}
			}
		});
		level.add(level_ton);
		
		
		editor = new JMenu("Editor");
		setStandardMenu(editor);
		menubar.add(editor);
		editor.setVisible(false);
		
		editor_new = new JMenuItem("New");
		setStandardMenu(editor_new);
		editor_new.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Leveleditor.level.deleteLevel(Leveleditor.canvas);
				
			}
		});
		editor.add(editor_new);
		
		editor_load = new JMenuItem("Load");
		setStandardMenu(editor_load);
		editor.add(editor_load);
		
		editor_save = new JMenuItem("Save");
		setStandardMenu(editor_save);
		editor_save.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				try {
					Serializer.write(Leveleditor.level, Leveleditor.getPanel());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		editor.add(editor_save);

		
		/*Info*/
		info = new JMenu("?");
		setStandardMenu(info);
		menubar.add(info);
		
		info_screen = new JMenuItem("Über...");
		setStandardMenu(info_screen);
		info_screen.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				Info info = new Info();
				info.setVisible(true);
			}
		});
		info.add(info_screen);
	}
	
	
	public void setStandard(){
		datei.setVisible(true);
		state= Menu_State.MODUS;
	}
	
	void setModus(){
		modus.setVisible(true);
		
	}
	private void setSingleplayer(){
		level.setVisible(true);
		state=Menu_State.SINGLEPLAYER;
	}
	
	
	private void setLeveleditor()
	{
		editor.setVisible(true);
		state=Menu_State.LEVELEDITOR;
		
	}
	private void toStandard(){
		switch(state){
		case MODUS: 
			modus.setVisible(false);
			break;
		case SINGLEPLAYER:
			level.setVisible(false);
			break;
		case LEVELEDITOR:
			editor.setVisible(false);
			break;
		default: 
			break;
		
		}
	}

	public  void changeMenu(Menu_State state){
		if(this.state!=state){
			toStandard();
			
			switch(state){
			case MODUS: setModus();break;
			case SINGLEPLAYER: setSingleplayer();break;
			case MULTIPLAYER: setMultiplayer();break;
			case LEVELEDITOR:setLeveleditor();break;
			default: break;
			}
		}
	}
	



	private void setMultiplayer()
	{
		// TODO Auto-generated method stub
		
	}


	private void setStandardMenu(Component comp)
	{
		// TODO Auto-generated method stub
		comp.setFont(StandardContent.neuropolFont(Font.BOLD, 13F));
		
	}
	public void setMenu(){
		Fullscreen.desktopPane.add(menubar);
	}
	
}
