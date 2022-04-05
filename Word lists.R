#### required packages ####
require(dplyr)
require(stringr)
require(stringi)


#### load data ####
words <- read.table(url("https://raw.githubusercontent.com/dwyl/english-words/master/words.txt"), 
                    encoding = "UTF-8")
worte <- read.table(url("https://gist.githubusercontent.com/MarvinJWendt/2f4f4154b8ae218600eb091a5706b5f4/raw/36b70dd6be330aa61cd4d4cdfda6234dcb0b8784/wordlist-german.txt"), 
                    encoding = "UTF-8")


#### prepare data ####
# transform to latin-1 standard
words$V1 <- stri_trans_general(words$V1, "Latin-ASCII")
worte$V1 <- stri_trans_general(worte$V1, "Latin-ASCII")
# get unique words without special characters
words <- unique(words) %>% 
  anti_join(filter(words, 
                   grepl("[[:punct:]]|[[:digit:]]|[[:space:]]|ä|ö|ü|Ä|Ö|Ü", 
                         words$V1)))
worte <- unique(worte) %>%
  anti_join(filter(worte, 
                   grepl("[[:punct:]]|[[:digit:]]|[[:space:]]|ä|ö|ü|Ä|Ö|Ü", 
                         worte$V1)))
# normalize the words to lowercase and save the columns as vectors
words <- casefold(words$V1)
worte <- casefold(worte$V1)

# function to get all words with n letters in a given vector
# words: character vector, n: int
nLettersWords <- function(words, n)
{
  na.omit(words[nchar(words, type = "char", n) == n])
}

# function to get all words with n unique letters
# words: character vector, n: int
nUniqueCharsWords <- function(words, n)
{
  words[unlist(lapply(strsplit(words, split = ""), 
                      function(x) { length(unique(x)) == n } ))]
}


#### export data ####
# data frame with columns for five letter words and their single letters
# multiple occurrence of letters possible
words <- nLettersWords(words, n = 5)
worte <- nLettersWords(worte, n = 5)
write.table(data.frame(words), "english five multiple letters words.txt",
            row.names = FALSE, col.names = FALSE, quote = FALSE)
write.table(data.frame(worte), 
            "german five multiple letters words.txt",
            row.names = FALSE, col.names = FALSE, quote = FALSE)
# only unique letters
write.table(data.frame(nUniqueCharsWords(words, 5)), 
            "english five unique letters words.txt", row.names = FALSE, 
            col.names = FALSE, quote = FALSE)
write.table(data.frame(nUniqueCharsWords(worte, 5)), 
            "german five unique letters words.txt", row.names = FALSE, 
            col.names = FALSE, quote = FALSE)