
public class StartPos
{
	public String name;
	public double pos_east, pos_height, pos_north;
	public double or_z, or_x, or_y;
	public double initSpeed;
	public double control_throttle;
	public boolean control_gear;

	public StartPos(String t_name, double t_pos_east, double t_pos_height, double t_pos_north, double t_or_z, double t_or_x, double t_or_y, double t_initSpeed, double t_control_throttle, boolean  t_control_gear)
	{
		name = t_name;

		pos_east = t_pos_east;
		pos_height = t_pos_height;
		pos_north = t_pos_north;

		or_z = t_or_z;
		or_x = t_or_x;
		or_y = t_or_y;

		initSpeed = t_initSpeed;
		control_throttle = t_control_throttle;
		control_gear = t_control_gear;
	}

}