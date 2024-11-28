
import java.net.URL;
import java.nio.file.Path;
import javax.sound.sampled.*;

import dominios.Musica;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Definindo as músicas
        Musica musicaTeenTitans = new Musica();
        musicaTeenTitans.setNome("Tema Jovens Titans");
        musicaTeenTitans.setDuracao(137);
        musicaTeenTitans.setArtista("Desconhecido");
        musicaTeenTitans.setCaminho("./assets/Teen-Titans-Theme.wav");

        Musica musicaKick = new Musica();
        musicaKick.setNome("Tema Kick Buttowski");
        musicaKick.setDuracao(167);
        musicaKick.setArtista("Desconhecido");
        musicaKick.setCaminho("./assets/Kick-Buttowski-Intro.wav");

        Musica musicaCarranca = new Musica();
        musicaCarranca.setNome("Que Bonita Sua Roupa");
        musicaCarranca.setDuracao(163);
        musicaCarranca.setArtista("Desconhecido");
        musicaCarranca.setCaminho("./assets/Chaves-Que-Bonita-Sua-Roupa.wav");

        List<Musica> musicas = List.of(musicaTeenTitans, musicaKick, musicaCarranca);

        System.out.println("auu");

        int atual = 0; 
        boolean inicio = true;
        Scanner scanner = new Scanner(System.in); 

        Clip oClip = AudioSystem.getClip();

        while (inicio) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Passar música");
            System.out.println("2 - Voltar música");
            System.out.println("3 - Parar");
            System.out.println("4 - Tocar");
            System.out.println("5 - Sair");
            System.out.print("Digite sua opção: ");

            int opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    System.out.println("Passar música");
                    if (atual < musicas.size() - 1) {
                        atual++;
                        tocarMusica(oClip, musicas.get(atual).getCaminho());
                    System.out.println("Tocando " + musicas.get(atual).getNome() + " de "
                            + musicas.get(atual).getArtista());
                    } else {
                        System.out.println("Última música da lista.");
                    }
                    
                    break;

                case 2:
                    System.out.println("Voltar música");
                    if (atual > 0) {
                        atual--;
                        tocarMusica(oClip, musicas.get(atual).getCaminho());
                    System.out.println("Tocando " + musicas.get(atual).getNome() + " de "
                            + musicas.get(atual).getArtista());
                    } else {
                        System.out.println("Já está na primeira música.");
                    }
                    
                    break;

                case 3:
                    System.out.println("Parar");
                    oClip.stop();
                    break;

                case 4:
                    System.out.println("Tocar");
                    tocarMusica(oClip, musicas.get(atual).getCaminho());
                    System.out.println("Tocando " + musicas.get(atual).getNome() + " de "
                            + musicas.get(atual).getArtista());
                    break;

                case 5:
                    System.out.println("Encerrando o programa...");
                    oClip.close();
                    inicio = false;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        scanner.close();
    }


    private static void tocarMusica(Clip oClip, String caminhoMusica) {
        try {
            if (oClip.isRunning()) {
                oClip.stop();
            }
            oClip.close();

            URL oUrl = Path.of(caminhoMusica).toUri().toURL();
            AudioInputStream oStream = AudioSystem.getAudioInputStream(oUrl);

            oClip.open(oStream);
            oClip.start();
        } catch (Exception error) {
            System.err.println("Erro ao reproduzir a música: " + error.getMessage());
        }
    }

}
