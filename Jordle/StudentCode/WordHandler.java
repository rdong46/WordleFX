import java.util.Random;
/**
 * This represents the class that consists of static methods which interactions with the word.
 * @author rdong46
 * @version 13.3.1
 */
public class WordHandler {
    private static String currentWord;
    /**
     *
     * @return the random word that was generated from a list of given words.
     */
    public static String randomWord() {
        Random rand = new Random();
        String randomWord = Words.list.get(rand.nextInt(Words.list.size()));
        currentWord = randomWord;
        return randomWord;
    }
    /**
     *
     * @param enteredWord the guess that the user entered
     * @param currentColumn the current column that the user is on
     * @return the status of each individual tiles based on the character at that tile
     */
    public static Status checkIndex(String enteredWord, int currentColumn) {
        if (currentWord.charAt(currentColumn) == (enteredWord.charAt(currentColumn))) {
            return Status.CORRECT;
        } else if (currentWord.contains(Character.toString(enteredWord.charAt(currentColumn)))) {
            if (currentWord.substring(currentWord.indexOf(Character.toString(
                    enteredWord.charAt(currentColumn))) + 1, enteredWord.length()).contains(
                        Character.toString(enteredWord.charAt(currentColumn)))) {
                if (enteredWord.substring(0, currentColumn).contains(
                        Character.toString(enteredWord.charAt(currentColumn)))) {
                    if (enteredWord.substring(enteredWord.substring(0, currentColumn).indexOf(
                            Character.toString(enteredWord.charAt(currentColumn))) + 1, currentColumn).contains(
                            Character.toString(enteredWord.charAt(currentColumn)))) {
                        return Status.WRONG;
                    } else if (enteredWord.substring(currentColumn + 1, enteredWord.length()).contains(
                            Character.toString(enteredWord.charAt(currentColumn)))) {
                        int index1 = enteredWord.substring(currentColumn + 1, enteredWord.length()).indexOf(
                                Character.toString(enteredWord.charAt(currentColumn))) + (currentColumn + 1);
                        if (enteredWord.substring(index1 + 1, enteredWord.length()).contains(
                                Character.toString(enteredWord.charAt(currentColumn)))) {
                            int index2 = enteredWord.substring(index1 + 1, enteredWord.length()).indexOf(
                                    Character.toString(enteredWord.charAt(currentColumn))) + index1;
                            if (enteredWord.substring(index2 + 1, enteredWord.length()).contains(
                                    Character.toString(enteredWord.charAt(currentColumn)))) {
                                return Status.WRONG;
                            } else {
                                if (enteredWord.charAt(index1) == currentWord.charAt(index1)
                                    || enteredWord.charAt(index2) == currentWord.charAt(index2)) {
                                    return Status.WRONG;
                                } else {
                                    return Status.SIMILAR;
                                }
                            }
                        } else {
                            if (enteredWord.charAt(index1) == currentWord.charAt(index1)) {
                                return Status.WRONG;
                            } else {
                                return Status.SIMILAR;
                            }
                        }
                    } else {
                        return Status.SIMILAR;
                    }
                } else if (enteredWord.substring(currentColumn + 1, enteredWord.length()).contains(
                        Character.toString(enteredWord.charAt(currentColumn)))) {
                    return checkIndexShort(enteredWord, currentColumn);
                } else {
                    return Status.SIMILAR;
                }
            } else if (enteredWord.substring(0, currentColumn).contains(
                Character.toString(enteredWord.charAt(currentColumn)))) {
                return Status.WRONG;
            } else if (enteredWord.substring(currentColumn + 1, enteredWord.length()).contains(
                    Character.toString(enteredWord.charAt(currentColumn)))) {
                int index1 = enteredWord.substring(currentColumn + 1, enteredWord.length()).indexOf(
                        Character.toString(enteredWord.charAt(currentColumn))) + (currentColumn + 1);
                if (enteredWord.charAt(index1) == currentWord.charAt(index1)) {
                    return Status.WRONG;
                } else if (enteredWord.substring((currentColumn < 4 ? currentColumn + 2 : 5),
                    enteredWord.length()).contains(Character.toString(enteredWord.charAt(currentColumn)))) {
                    int index2 = enteredWord.substring(currentColumn + 2, enteredWord.length()).indexOf(
                            Character.toString(enteredWord.charAt(currentColumn))) + (currentColumn + 2);
                    if (enteredWord.charAt(index2) == currentWord.charAt(index2)) {
                        return Status.WRONG;
                    } else if (enteredWord.substring((currentColumn < 3 ? currentColumn + 3 : 5),
                        enteredWord.length()).contains(Character.toString(enteredWord.charAt(currentColumn)))) {
                        int index3 = enteredWord.substring(currentColumn + 3, enteredWord.length()).indexOf(
                            Character.toString(enteredWord.charAt(currentColumn))) + (currentColumn + 3);
                        if (enteredWord.charAt(index3) == currentWord.charAt(index3)) {
                            return Status.WRONG;
                        } else if (enteredWord.substring((currentColumn < 2 ? currentColumn + 4 : 5),
                            enteredWord.length()).contains(Character.toString(enteredWord.charAt(currentColumn)))) {
                            int index4 = enteredWord.substring(currentColumn + 4, enteredWord.length()).indexOf(
                                    Character.toString(enteredWord.charAt(currentColumn))) + (currentColumn + 4);
                            if (enteredWord.charAt(index4) == currentWord.charAt(index4)) {
                                return Status.WRONG;
                            } else {
                                return Status.SIMILAR;
                            }
                        } else {
                            return Status.SIMILAR;
                        }
                    } else {
                        return Status.SIMILAR;
                    }
                } else {
                    return Status.SIMILAR;
                }
            } else {
                return Status.SIMILAR;
            }
        } else {
            return Status.WRONG;
        }
    }
    /**
     *
     * @param enteredWord the word the user entered
     * @param currentColumn the currenc column the user is on
     * @return the status of the tile
     */
    public static Status checkIndexShort(String enteredWord, int currentColumn) {
        int index1 = enteredWord.substring(currentColumn + 1, enteredWord.length()).indexOf(
            Character.toString(enteredWord.charAt(currentColumn))) + (currentColumn + 1);
        if (enteredWord.substring(index1 + 1, enteredWord.length()).contains(
                Character.toString(enteredWord.charAt(currentColumn)))) {
            int index2 = enteredWord.substring(index1 + 1, enteredWord.length()).indexOf(
                    Character.toString(enteredWord.charAt(currentColumn))) + index1 + 1;
            if (enteredWord.substring(index2 + 1, enteredWord.length()).contains(
                    Character.toString(enteredWord.charAt(currentColumn)))) {
                int index3 = enteredWord.substring(index2 + 1, enteredWord.length()).indexOf(
                        Character.toString(enteredWord.charAt(currentColumn))) + index2 + 1;
                if (enteredWord.substring(index3 + 1, enteredWord.length()).contains(
                        Character.toString(enteredWord.charAt(currentColumn)))) {
                    int index4 = enteredWord.substring(index3 + 1, enteredWord.length()).indexOf(
                            Character.toString(enteredWord.charAt(currentColumn))) + index3 + 1;
                    if (currentWord.charAt(index1) == enteredWord.charAt(index1)) {
                        if (currentWord.charAt(index2) == enteredWord.charAt(index2)) {
                            return Status.WRONG;
                        } else if (currentWord.charAt(index3) == enteredWord.charAt(index3)) {
                            return Status.WRONG;
                        } else if (currentWord.charAt(index4) == enteredWord.charAt(index4)) {
                            return Status.WRONG;
                        } else {
                            return Status.SIMILAR;
                        }
                    } else if (currentWord.charAt(index2) == enteredWord.charAt(index2)) {
                        if (currentWord.charAt(index3) == enteredWord.charAt(index3)) {
                            return Status.WRONG;
                        } else if (currentWord.charAt(index4) == enteredWord.charAt(index4)) {
                            return Status.WRONG;
                        } else {
                            return Status.SIMILAR;
                        }
                    } else if (currentWord.charAt(index3) == enteredWord.charAt(index3)) {
                        if (currentWord.charAt(index4) == enteredWord.charAt(index4)) {
                            return Status.WRONG;
                        } else {
                            return Status.SIMILAR;
                        }
                    } else {
                        return Status.SIMILAR;
                    }
                } else {
                    if (currentWord.charAt(index1) == enteredWord.charAt(index1)) {
                        if (currentWord.charAt(index2) == enteredWord.charAt(index2)) {
                            return Status.WRONG;
                        } else if (currentWord.charAt(index3) == enteredWord.charAt(index3)) {
                            return Status.WRONG;
                        } else {
                            return Status.SIMILAR;
                        }
                    } else if (currentWord.charAt(index2) == enteredWord.charAt(index2)) {
                        if (currentWord.charAt(index3) == enteredWord.charAt(index3)) {
                            return Status.WRONG;
                        } else {
                            return Status.SIMILAR;
                        }
                    } else {
                        return Status.SIMILAR;
                    }
                }
            } else {
                if (currentWord.charAt(index1) == enteredWord.charAt(index1)) {
                    if (currentWord.charAt(index2) == enteredWord.charAt(index2)) {
                        return Status.WRONG;
                    } else {
                        return Status.SIMILAR;
                    }
                } else {
                    return Status.SIMILAR;
                }
            }
        } else {
            return Status.SIMILAR;
        }
    }
}