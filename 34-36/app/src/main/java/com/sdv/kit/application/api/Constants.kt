package com.sdv.kit.application.api

const val CLIENT_ID = "C4IV0FZXIV3PMCMB1OMVSZ2LFDSJA4XHVPFIAD3ITWNBO0MG"
const val CLIENT_SECRET = "2WB1FLJOGUHVTNXESSTRCKSZCFL3ZM1KQIZYYLTVQUXRTZLP"
const val REDIRECT_URI = "innowiseapp://callback"
const val API_KEY = "fsq3gmO++suZHiZqTPpsuNl6K3BuvAxHDLSUsitEnzF4YOo="

const val BASE_API_URL = "https://api.foursquare.com/v3/"
const val BASE_AUTH_URL = "https://foursquare.com/oauth2/"
const val AUTH_PAGE_URI = "${BASE_AUTH_URL}/authenticate?client_id=$CLIENT_ID&response_type=code&redirect_uri=$REDIRECT_URI"

const val LATITUDE = "53.9057644"
const val LONGITUDE = "27.5582305"
const val RADIUS = 4000
const val LIMIT = 30