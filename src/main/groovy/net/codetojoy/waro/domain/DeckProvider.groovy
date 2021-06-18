package net.codetojoy.waro.casino

import groovy.transform.NullCheck

@NullCheck
class DeckProvider {
   def numCards
   DeckProvider() {}

   DeckProvider(def numCards) {
	this.numCards = numCards
   }

   def newDeck() {
        def deck = []

        (1..numCards).each { deck << it }

        deck.shuffle()

        return deck
   }
}