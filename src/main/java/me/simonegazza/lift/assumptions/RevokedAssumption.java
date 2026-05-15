package me.simonegazza.lift.assumptions;

import java.util.List;

/**
 * Immutable container used to transfer information between the Lifter and the
 * {@link Assumer}.
 * <p>
 * Represents a specific assumption instance that must be revoked, identified by
 * its original parameter name and its position inside a possibly
 * multidimensional structure.
 * </p>
 *
 * @param name    the original name of the assumption to revoke
 * @param indices the list of indices identifying the exact position of this
 *                    parameter within its lifted structure; may be empty but
 *                    must never be null
 */
public record RevokedAssumption(String name, List<Integer> indices) {

}
