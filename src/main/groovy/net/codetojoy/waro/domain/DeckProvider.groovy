package net.codetojoy.waro.casino

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