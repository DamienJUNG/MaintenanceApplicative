package com.gildedrose;

class GildedRose {
    StockItem[] items;

    public GildedRose(StockItem[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            items[i].updateQuality();
        }
    }
}
