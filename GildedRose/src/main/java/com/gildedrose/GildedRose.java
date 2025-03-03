package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            handleItem(items[i]);
        }
    }
    
    public void handleItem(Item item) {
        switch (item.name) {
            case AGED_BRIE -> handleBrie(item);
            case TAFKAL_80_ETC_CONCERT -> handleTaflkalPasses(item);
            case SULFURAS -> handleSulfuras(item);
            default -> handleGenericItem(item);
        }
    }

    private void handleGenericItem(Item item) {
        item.sellIn = item.sellIn - 1;

        if(item.quality > 0){
            item.quality = item.quality - 1;
            if(item.quality > 0 && item.sellIn < 0) {
                item.quality = item.quality - 1;
            }
        }
    }

    private void handleSulfuras(Item item) {
        //Sulfuras is a legendary item
    }

    private void handleTaflkalPasses(Item item) {
        item.sellIn = item.sellIn - 1;

        if(item.quality < 50){
            if(item.sellIn < 0){
                item.quality = 0;
            }
            else if(item.sellIn < 6){
                item.quality = item.quality + 3;
            }
            else if(item.sellIn < 11){
                item.quality = item.quality + 2;
            }
            else {
                item.quality += 1;
            }

            if(item.quality > 50){
                item.quality = 50;
            }
        }
    }

    private void handleBrie(Item item) {
        item.sellIn = item.sellIn - 1;

        if(item.quality < 50){
            item.quality = item.quality + 1;

            if(item.quality < 50 && item.sellIn < 0){
                item.quality = item.quality + 1;
            }
        }

    }
}
