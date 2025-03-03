package com.gildedrose;

public class BackStagePass extends StockItem {
    public BackStagePass(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }
    
    public void updateQuality() {
        this.sellIn = this.sellIn - 1;

        if(this.quality < 50){
            if(this.sellIn < 0){
                this.quality = 0;
            }
            else if(this.sellIn < 6){
                this.quality = this.quality + 3;
            }
            else if(this.sellIn < 11){
                this.quality = this.quality + 2;
            }
            else {
                this.quality += 1;
            }

            if(this.quality > 50){
                this.quality = 50;
            }
        }
    }
}
