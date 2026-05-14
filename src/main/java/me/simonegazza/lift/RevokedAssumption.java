package me.simonegazza.lift;

import java.util.List;

/**
 * Container class useful for passing informations between the Lifter and the
 * Assumer.
 *
 * @param name    the name of the assumption to revoke
 * @param indices a list of indices representing the position of this specific
 *                    parameter (note that the list can be empty but not null)
 */
public record RevokedAssumption(String name, List<Object> indices) {

}
