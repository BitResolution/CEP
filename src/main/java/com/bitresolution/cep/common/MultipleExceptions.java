package com.bitresolution.cep.common;

import java.util.ArrayList;
import java.util.List;

public class MultipleExceptions extends Exception {

    private static final long serialVersionUID = 8187488971933967146L;

    private final List<Exception> exceptions;

    public MultipleExceptions() {
        this.exceptions = new ArrayList<Exception>();
    }

    /**
     * Adds the specified exception to the multiple exception, up to the limit.
     *
     * @param exception the exception.
     * @return true if it was/were added or false otherwise.
     */
    public boolean add(final Exception exception) {

        return add(exception, null);
    }

    /**
     * Adds the specified exception to the multiple exception, up to the limit.
     *
     * @param exception the exception.
     * @param id        an id to add to the end of the message. It may be null.
     * @return true if it was/were added or false otherwise.
     */
    public boolean add(final Exception exception, final String id) {
        if(exception instanceof MultipleExceptions) {
            for(final Exception exceptions : ((MultipleExceptions) exception).exceptions) {
                if(!this.exceptions.add(exceptions)) {
                    return false;
                }
            }
        }
        else {
            this.exceptions.add(exception);
        }

        return true;
    } // add()

    public int getNumExceptions() {

        return this.exceptions.size();
    } // getNumExceptions()

    public Exception getException(final int index) {

        return this.exceptions.get(index);
    } // getException()

    @Override
    public String getMessage() {

        final StringBuilder buf = new StringBuilder();

        buf.append("There are ");
        buf.append(getNumExceptions());
        buf.append(" errors:\n");
        for(final Exception excp : this.exceptions) {
            buf.append("\t");
            buf.append(excp.getMessage());
            buf.append('\n');
        }

        return buf.toString();
    } // getMessage()

} // end class MultipleExceptions
