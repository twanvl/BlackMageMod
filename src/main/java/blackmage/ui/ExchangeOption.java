package blackmage.ui;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import blackmage.BlackMageMod;
import blackmage.effects.CampfireExchangeEffect;

public class ExchangeOption extends AbstractCampfireOption {

	public static final String LABEL = "Exchange";
	public static final String DESCRIPTION = "Exchange your Ice and Fire cards versa.";
	
	public ExchangeOption(boolean active) {
		this.label = "Exchange";
		this.useable = active;
		if (active) {
			this.description = DESCRIPTION;
			this.img = BlackMageMod.getCampfireExchangeButton();
		}else {
			this.description = DESCRIPTION;
			this.img = BlackMageMod.getCampfireExchangeButton();
		}
	}
	
	@Override
	public void useOption() {
		if(this.useable) {
			AbstractDungeon.effectList.add(new CampfireExchangeEffect());
		}
	}

}
