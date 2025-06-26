package nostalgia.devices.nokia3310.apps.common

import androidx.annotation.RawRes
import nostalgia.devices.nokia3310.R

enum class Ringtone(val ringtoneName: String, @RawRes val audioRes: Int) {

    ATTRACTION("Attraction", R.raw.attraction),
    AULD_LANG_SYNE("Auld Lang Syne", R.raw.auld_lang_syne),
    BADINERIE("Badinerie", R.raw.badinerie),
    BEE("Bee", R.raw.bee),
    CITY_BIRD("City Bird", R.raw.city_bird),
    DAWN("Dawn", R.raw.dawn),
    FROG("Frog", R.raw.frog),
    GET_COOL("Get Cool", R.raw.get_cool),
    GROOVY_BLUE("Groovy Blue", R.raw.groovy_blue),
    HOPPING_DOWN("Hopping Down", R.raw.hopping_down),
    HURDY_GURDY("Hurdy Gurdy", R.raw.hurdy_gurdy),
    IMPROVISATION("Improvisation", R.raw.improvisation),
    INDIFFERENCE("Indifference", R.raw.indifference),
    JUMPING("Jumping", R.raw.jumping),
    KNICK_KNACK("Knick Knack", R.raw.knick_knack),
    LOW("Low", R.raw.low),
    MANGROVE("Mangrove", R.raw.mangrove),
    MERRY_XMAS("Merry X-Mas", R.raw.merry_xmas),
    MOZART_40("Mozart 40", R.raw.mozart_40),
    NOKIA_TUNE("Nokia Tune", R.raw.nokia_tune),
    PLAYGROUND("Playground", R.raw.playground),
    POLITE("Polite", R.raw.polite),
    RING_RING("Ring Ring", R.raw.ring_ring),
    ROCKET("Rocket", R.raw.rocket),
    SAMBA_RUMBLE("Samba Rumble", R.raw.samba_rumble),
    SUNNY_WALKS("Sunny Walks", R.raw.sunny_walks),
    THATS_IT("That's It", R.raw.thats_it),
    THE_BUFFOON("The Buffoon", R.raw.the_buffoon),
    TOREADOR("Toreador", R.raw.toreador)
}