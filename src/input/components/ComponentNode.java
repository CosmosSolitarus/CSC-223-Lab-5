package input.components;

/**
 * I have no idea if this is working as intended but all tests passed
 * so it's probably fine.
 */
public interface ComponentNode
{
	Object accept(ComponentNodeVisitor visitor, Object o);
}
