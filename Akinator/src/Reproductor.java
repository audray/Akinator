
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ari
 */
public class Reproductor {
    private Clip cancion;
    
    public Reproductor(){
        try {
            // Se obtiene un Clip de sonido
            cancion = AudioSystem.getClip();
            // Se carga con un fichero wav
            cancion.open(AudioSystem.getAudioInputStream(new File("OnceUponaDream.wav")));
        } catch (Exception exception) {
            Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, exception);
        } 
            
    }
    
    public void play(){
        cancion.start();
        cancion.loop(10);
    }
    
    public void pause(){
        cancion.stop();
    }
    
}
