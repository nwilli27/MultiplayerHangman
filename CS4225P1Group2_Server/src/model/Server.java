package model;

public class Server {

	private RequestHandler requestHandler;
	
	public Server()
	{
		this.requestHandler = new RequestHandler();
	}
	
	public void Run()
	{
		// Run server endlessly handling request until some form of end occurs (unknown atm)
		while (true)
		{
			// Need to get next message from client
			var request = "";
			this.requestHandler.handleRequest(request);
		}
	}
}
