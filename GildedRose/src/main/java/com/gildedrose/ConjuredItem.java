package com.gildedrose;

public class ConjuredItem extends StockItem {
    public ConjuredItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    void updateQuality() {
        this.sellIn -= 1;
        this.quality -= 2;

        if (this.sellIn < 0) {
            this.quality -= 2;
        }

        if (this.quality < 0) {
            this.quality = 0;
        }
    }


}
