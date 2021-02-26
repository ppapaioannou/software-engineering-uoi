/*
** May 2020 
**
*****************************TuneEncoding*****************************
**********************************************************************
** TuneEncoding has a smart way to set the current encoding strategy.
** This command synergizes with the implementation of a JComboBox,
** it never needs to know which strategy is being set as long as 
** the StrategiesFactory is set up properly.
**
** To add any new encoding strategies there are three simple steps,
*
** 1 - Create a new encoding class that inherits the 
**     TemplateEncoding abstract class and 
**     implement the map(char) method.
*
** 2 - Add the new strategy to the StrategiesFactory.
*
** 3 - Add the new strategy to the list of strategies at the 
**     MenuManager.
** 
** That's it!
**********************************************************************
**
**
*/
package commands;

import javax.swing.JComboBox;

import encodingstrategies.StrategiesFactory;
import model.Document;

public class TuneEncoding extends Command{

	private JComboBox<String> comboBox;
	
	private Document currentDocument;
	
	private String strategyType;
	
	
	public TuneEncoding(JComboBox<String> comboBox, Document currentDocument) {
		this.comboBox=comboBox;
		this.currentDocument=currentDocument;
		
	}
	
	public TuneEncoding(Document currentDocument, String strategyType) {
		this.currentDocument=currentDocument;
		this.strategyType=strategyType;
		
	}

	@Override
	public void doWork() {
		String strategy = (String) comboBox.getSelectedItem();
		strategyType = strategy.replace(" ","");
		execute();
		commands.add(copy());
		
	}

	@Override
	public void execute() {
		currentDocument.setEncodingStrategy(StrategiesFactory.createStrategy(strategyType));
		
	}

	@Override
	public Command copy() {
		Document newCurrentDocument =new Document();
		newCurrentDocument=this.currentDocument;
		return new TuneEncoding(newCurrentDocument, new String (this.strategyType));
	}

}
