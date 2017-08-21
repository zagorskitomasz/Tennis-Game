# Tennis-Game
Tennis arcade game. Using Processing, Minim and Swing libraries. My recent Java project.

21.08.2017: Added some Lambda Expressions, so I could remove 3 useless classes. Example:

		tUpSt = new Timer(8000, event -> {
			this.powerUp = new PowerUp(this);
			this.tUp.stop();
			this.tUp = new Timer(20, this.powerUp);
			this.tUp.start();
		});
