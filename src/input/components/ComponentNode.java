package input.components;

/**
 * I have no idea if this is working as intended but all tests passed
 * so it's probably fine.
 */
public interface ComponentNode
{
	void unparse(StringBuilder sb, int level);
}
