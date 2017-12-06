package fi.konstal.example.game2;

import fi.konstal.engine.core.GameLoop;
import fi.konstal.engine.gameobject.Enemy;
import fi.konstal.engine.gameobject.GameActor;
import fi.konstal.engine.gameobject.Zone;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Projectile;
import fi.konstal.engine.util.Sprite;
import fi.konstal.engine.util.SpriteAnimation;

public class EnemyCarrier extends GameActor {
    private int counter;
    private Sprite missileSprite = new SpriteAnimation(
                "src/fi/konstal/example/game2/resources/enemyMissile.png",
                        1,
                        5,
                        60,
                        112,
                        5
    );

    public EnemyCarrier(int x, int y, int width, int height, Sprite sprite, int hp) {
        super(x, y, width, height, sprite, hp);
    }

    @Override
    public void move(Map map) {
        setX(getX() + getxVelocity());
        setY(getY() + getyVelocity());
        getCollider().update(getX(), getY());
    }


    @Override
    public void handleCollision(Zone c) {
        if(c instanceof Projectile) {
            setHp(getHp() - ((Projectile) c).getDamage());

            if(getHp() <= 0) {
                setAlive(false);
            }

        }
        System.out.println("collision on enemy class");
    }

    public void fireProjectile() {
        Missile temp = new Missile(
                getX() + getWidth()/2 -20,
                getY() -50,
                40,
                50,
                missileSprite,
                10
        );
        temp.setyVelocity(5);
        temp.setParent(this.getClass());
        //projectiles.add(temp);
        GameLoop.addGameObject(temp);
    }

    @Override
    public void update() {
        if(counter > 25) {
            setxVelocity((int)(Math.random()*30)-15);
            setyVelocity((int)(Math.random()*6)+1);

            if((int)(Math.random()*10) > 7) {
                fireProjectile();
            }
            counter = 0;
        } else {
            setxVelocity(0);
            setyVelocity(0);
            counter++;
        }




    }
}
