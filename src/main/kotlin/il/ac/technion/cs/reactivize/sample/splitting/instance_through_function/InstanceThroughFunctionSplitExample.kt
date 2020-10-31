package il.ac.technion.cs.reactivize.sample.splitting.instance_through_function

import il.ac.technion.cs.reactivize.sample.finance.QuoteGetter

/*
 Different objects have different usages of the price property
 one uses the observable, while the other accesses it directly, and another performs both

 The simple one will get the original class, while the others the reactivized one
 */
fun main() {
    val qgNeedsReactivize = QuoteGetter("GOOG")
    val qgSimple = QuoteGetter("GOOG")
    val qgBoth = QuoteGetter("GOOG")

    registerToQuoteGetterObservable(qgNeedsReactivize)
    simplePriceAccess(qgSimple)

    registerToQuoteGetterObservable(qgBoth)
    simplePriceAccess(qgBoth)

    Thread.sleep(5000)
}

fun registerToQuoteGetterObservable(qg: QuoteGetter) {
    qg.priceObservable.subscribe {
        println("reactivized price: $it")
    }
}

fun simplePriceAccess(qg: QuoteGetter) {
    println("Simple getPrice call: ${qg.price}")
}
