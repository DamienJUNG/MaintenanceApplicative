package com.gildedrose;

public class GenericItem extends StockItem {
    public GenericItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    void updateQuality() {
        this.sellIn = this.sellIn - 1;

        if(this.quality > 0){
            this.quality = this.quality - 1;
            if(this.quality > 0 && this.sellIn < 0) {
                this.quality = this.quality - 1;
            }
        }
    }


}
