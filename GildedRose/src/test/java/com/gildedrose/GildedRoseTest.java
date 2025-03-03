package com.gildedrose;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Nested
    class forAnyValidItem {
        @ParameterizedTest
        @MethodSource("itemProvider")
        public void updateShouldBeCorrect(StockItem item, int sellIn, int quality) {
            GildedRose app = new GildedRose(new StockItem[]{item});
            app.updateQuality();

            assertEquals(sellIn, app.items[0].sellIn);
            assertEquals(quality, app.items[0].quality);
            assertEquals(item.name+", "+item.sellIn+", "+item.quality, item.toString());
        }

        public static Stream<Arguments> itemProvider(){
            return Stream.of(
                    //Base test
                    Arguments.of(new AgedBrie("Aged Brie", 20,20), 19,21),
                    Arguments.of(new Sulfuras("Sulfuras, Hand of Ragnaros", 10, 80), 10, 80),
                    Arguments.of(new BackStagePass("Backstage passes to a TAFKAL80ETC concert", 100, 5), 99, 6),
                    Arguments.of(new BackStagePass("Backstage passes to a TAFKAL80ETC concert", 10, 5), 9, 7),
                    Arguments.of(new BackStagePass("Backstage passes to a TAFKAL80ETC concert", 5, 5), 4, 8),
                    Arguments.of(new BackStagePass("Backstage passes to a TAFKAL80ETC concert", 0, 5), -1, 0),
                    Arguments.of(new GenericItem("Patate", 10, 5), 9, 4),

                    //High Degradation
                    Arguments.of(new AgedBrie("Aged Brie", 0,20), -1,22),
                    Arguments.of(new Sulfuras("Sulfuras, Hand of Ragnaros", 0, 80), 0, 80),
                    Arguments.of(new BackStagePass("Backstage passes to a TAFKAL80ETC concert", 0, 5), -1, 0),
                    Arguments.of(new GenericItem("Patate", 0, 5), -1, 3),

                    //Null Quality
                    Arguments.of(new AgedBrie("Aged Brie", 10,0), 9,1),
                    Arguments.of(new BackStagePass("Backstage passes to a TAFKAL80ETC concert", 10, 0), 9, 2),
                    Arguments.of(new GenericItem("Patate", 10, 0), 9, 0),

                    //Max quality
                    Arguments.of(new AgedBrie("Aged Brie", 10,50), 9,50),
                    Arguments.of(new BackStagePass("Backstage passes to a TAFKAL80ETC concert", 15, 50), 14, 50),
                    Arguments.of(new BackStagePass("Backstage passes to a TAFKAL80ETC concert", 10, 50), 9, 50),
                    Arguments.of(new BackStagePass("Backstage passes to a TAFKAL80ETC concert", 4, 50), 3, 50),
                    Arguments.of(new GenericItem("Patate", 10, 50), 9, 49),

                    //Reaching Max Quality
                    Arguments.of(new AgedBrie("Aged Brie", 10,49), 9,50),
                    Arguments.of(new BackStagePass("Backstage passes to a TAFKAL80ETC concert", 15, 49), 14, 50),
                    Arguments.of(new BackStagePass("Backstage passes to a TAFKAL80ETC concert", 10, 49), 9, 50),
                    Arguments.of(new BackStagePass("Backstage passes to a TAFKAL80ETC concert", 4, 49), 3, 50),

                    //Rotted potatoes && sulfuras &&
                    Arguments.of(new GenericItem("Patate", 0, 50), -1, 48),
                    Arguments.of(new GenericItem("Patate", 0, 1), -1, 0),
                    Arguments.of(new GenericItem("Patate", 0, 0), -1, 0),
                    Arguments.of(new Sulfuras("Sulfuras, Hand of Ragnaros", -1, 80), -1, 80),
                    Arguments.of(new AgedBrie("Aged Brie", 0,50), -1,50),

                    //Conjured
                    Arguments.of(new ConjuredItem("Pancakes",10,0),9,0),
                    Arguments.of(new ConjuredItem("Pancakes",0,0),-1,0),
                    Arguments.of(new ConjuredItem("Pancakes",10,10),9,8),
                    Arguments.of(new ConjuredItem("Pancakes",0,10),-1,6)
            );
        }
    }

}
