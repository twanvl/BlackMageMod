package blackmage.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import blackmage.cards.AbstractCustomCardWithType;
import blackmage.patches.GridSelectScreenPatch;
import blackmage.ui.ExchangeOption;

public class CampfireExchangeEffect extends AbstractGameEffect{

	@SuppressWarnings("unused")
	private static final float DUR = 1.5f;
	private boolean openedScreen = false;
	private boolean selectedCard = false;
	private Color screenColor = AbstractDungeon.fadeColor.cpy();
	
	public CampfireExchangeEffect() {
		this.duration = 1.5f;
		this.screenColor.a = 0.0f;
		AbstractDungeon.overlayMenu.proceedButton.hide();
	}
	
	@Override
	public void render(SpriteBatch sb) {
		sb.setColor(this.screenColor);
	    sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
	    if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {
	    	AbstractDungeon.gridSelectScreen.render(sb);
	    }
	}
	
	private void updateBlackScreenColor()
	  {
	    if (this.duration > 1.0F) {
	      this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.0F) * 2.0F);
	    } else {
	      this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / 1.5F);
	    }
	  }
	
	@Override
	public void update() {
		if (!AbstractDungeon.isScreenUp) {
			this.duration -= Gdx.graphics.getDeltaTime();
			updateBlackScreenColor();
		}
		
		if ((!this.selectedCard) && (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty())) {
			CardCrawlGame.sound.play("CARD_EXHAUST");
			
			AbstractCustomCardWithType card = (AbstractCustomCardWithType)AbstractDungeon.gridSelectScreen.selectedCards.get(0);
			AbstractCustomCardWithType newCard = card.getOpposite(card.upgraded);
			
			AbstractDungeon.player.masterDeck.removeCard(card);
			AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(newCard, Settings.WIDTH / 2, Settings.HEIGHT / 2));
			AbstractDungeon.gridSelectScreen.selectedCards.clear();
			GridSelectScreenPatch.renderAsExchange = false;
			
			this.selectedCard = true;
			
			((RestRoom)AbstractDungeon.getCurrRoom()).fadeIn();
		}
		if ((this.duration < 1.0F) && (!this.openedScreen))
	    {
			this.openedScreen = true;
		      
		    CardGroup fire_ice_cards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
		      
		    for(AbstractCard card : AbstractDungeon.player.masterDeck.group) {
		    	if (card instanceof AbstractCustomCardWithType) {
		    		AbstractCustomCardWithType typeCard = (AbstractCustomCardWithType)card;
		    		if (typeCard.colorType == AbstractCustomCardWithType.CardColorType.ICE || typeCard.colorType == AbstractCustomCardWithType.CardColorType.FIRE) {
		    			if(typeCard.getOpposite(false) != null)
		    				fire_ice_cards.group.add(card);
		    		}
		    	}
		    }
		      
		    AbstractDungeon.gridSelectScreen.open(fire_ice_cards, 1, ExchangeOption.LABEL, true);
		    GridSelectScreenPatch.renderAsExchange = true;
		    //AbstractDungeon.overlayMenu.cancelButton.show("Cancel");
	    }
		if (this.duration < 0.0F)
	    {
			this.isDone = true;
			GridSelectScreenPatch.renderAsExchange = false;
			
			if (CampfireUI.hidden)
			{
				com.megacrit.cardcrawl.rooms.AbstractRoom.waitTimer = 0.0F;
				AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
				((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
			}
	    }
	}
	

}
