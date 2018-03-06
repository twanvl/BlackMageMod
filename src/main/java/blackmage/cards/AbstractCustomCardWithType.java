package blackmage.cards;

import basemod.abstracts.CustomCardWithRender;

public abstract class AbstractCustomCardWithType extends CustomCardWithRender{

	public enum CardColorType
	{
			ICE, FIRE, DARK;
			CardColorType() {}
	}
	
	public CardColorType colorType;
	
	public AbstractCustomCardWithType(String id, String name, String img, String bgTexture, String bgTexture_p,
			int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target,
			int cardPool, CardColorType damageType) {
		super(id, name, img, bgTexture, bgTexture_p, cost, rawDescription, type, color, rarity, target, cardPool);
		colorType = damageType;
	}
	
	public abstract AbstractCustomCardWithType getOpposite();
}
