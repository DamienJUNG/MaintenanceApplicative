package com.gildedrose;

public class AgedBrie extends StockItem{
    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        this.sellIn = this.sellIn - 1;

        if(this.quality < 50){
            this.quality = this.quality + 1;

            if(this.quality < 50 && this.sellIn < 0){
                this.quality = this.quality + 1;
            }
        }
    }
}
