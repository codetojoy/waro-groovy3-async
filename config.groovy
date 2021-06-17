
numCards = 40
numGames = 10

// Player requires: name, strategy
//
// available strategies:
// MaxCard, MinCard, NearestCard, PopCard, RandomCard
// Console (for interactive games)
// HybridThirds

String SCHEME = "http"
String HOST = "localhost:8080"
String PATH = "waro/strategy"
String MODE = "max"

players << new Player('Alice Api', new ApiRemote(SCHEME, HOST, PATH, MODE))
players << new Player('Michael Max', new MaxCard())
players << new Player('Nelly Nearest', new NearestCard())
players << new Player('Randy Random', new RandomCard())

/*
def top = new MaxCard()
def mid = new RandomCard()
def low = new MinCard()
def hybridThirds = new HybridThirds(top, mid, low)
players << new Player('Helen Hybrid', hybridThirds, numCards)
*/

